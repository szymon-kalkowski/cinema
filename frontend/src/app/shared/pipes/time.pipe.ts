import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'time',
})
export class TimePipe implements PipeTransform {
  public transform(value: string): string {
    const [hours, minutes] = value.split(':');

    return `${hours}:${minutes}`;
  }
}
