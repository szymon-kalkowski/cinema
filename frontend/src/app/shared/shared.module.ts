import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DurationPipe } from './pipes/duration.pipe';
import { TimePipe } from './pipes/time.pipe';

@NgModule({
  declarations: [DurationPipe, TimePipe],
  imports: [CommonModule],
  exports: [DurationPipe, TimePipe],
})
export class SharedModule {}
