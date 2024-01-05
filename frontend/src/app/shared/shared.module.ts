import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DurationPipe } from './pipes/duration.pipe';
import { TimePipe } from './pipes/time.pipe';
import { LoadingComponent } from './components/loading/loading.component';

@NgModule({
  declarations: [DurationPipe, TimePipe, LoadingComponent],
  imports: [CommonModule],
  exports: [DurationPipe, TimePipe, LoadingComponent],
})
export class SharedModule {}
