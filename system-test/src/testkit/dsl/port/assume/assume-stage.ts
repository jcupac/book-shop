import type { AssumeRunning } from './steps/assume-running.js';

export interface AssumeStage {
  bookShop(): AssumeRunning;
  erp(): AssumeRunning;
  tax(): AssumeRunning;
  clock(): AssumeRunning;
}
