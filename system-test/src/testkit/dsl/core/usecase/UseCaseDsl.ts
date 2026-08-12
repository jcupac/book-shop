import type { AppContext, ChannelMode } from '../scenario/app-context.js';
import { UseCaseContext, type ExternalSystemMode } from '../shared/use-case-context.js';
import { BookShopDsl } from './BookShopDsl.js';
import { ClockDsl } from './external/clock/ClockDsl.js';
import { ErpDsl } from './external/erp/ErpDsl.js';
import { TaxDsl } from './external/tax/TaxDsl.js';

function resolveExternalSystemMode(): ExternalSystemMode {
  return (process.env.EXTERNAL_SYSTEM_MODE as ExternalSystemMode) ?? 'real';
}

export class UseCaseDsl {
  private readonly useCaseContext: UseCaseContext;
  private _bookShopDsl?: BookShopDsl;
  private _clockDsl?: ClockDsl;
  private _erpDsl?: ErpDsl;
  private _taxDsl?: TaxDsl;

  constructor(private readonly app: AppContext) {
    this.useCaseContext = new UseCaseContext(resolveExternalSystemMode());
  }

  bookShop(mode?: ChannelMode): BookShopDsl {
    if (mode) {
      this._bookShopDsl = new BookShopDsl(this.app.bookShop(mode), this.useCaseContext);
    } else {
      this._bookShopDsl ??= new BookShopDsl(this.app.bookShop(mode), this.useCaseContext);
    }
    return this._bookShopDsl;
  }

  clock(): ClockDsl {
    this._clockDsl ??= new ClockDsl(this.app.clockDriver, this.useCaseContext);
    return this._clockDsl;
  }

  erp(): ErpDsl {
    this._erpDsl ??= new ErpDsl(this.app.erpDriver, this.useCaseContext);
    return this._erpDsl;
  }

  tax(): TaxDsl {
    this._taxDsl ??= new TaxDsl(this.app.taxDriver, this.useCaseContext);
    return this._taxDsl;
  }

  async close(): Promise<void> {
    await this.app.closeAll();
  }
}
