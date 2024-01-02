import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'duration',
})
export class DurationPipe implements PipeTransform {
  public transform(value: number): string {
    const hours: number = Math.floor(value / 60);
    const minutes: number = value % 60;
    const displayedHours: string = hours < 10 ? `0${hours}` : `${hours}`;
    const displayedMinutes: string =
      minutes < 10 ? `0${minutes}` : `${minutes}`;

    return hours > 0
      ? `${displayedHours}:${displayedMinutes} h`
      : `${displayedMinutes} min`;
  }
}
