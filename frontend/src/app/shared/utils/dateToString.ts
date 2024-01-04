export const dateToString = (date: Date): string => {
  const day: number = date.getDate();
  const month: number = date.getMonth() + 1;
  const year: number = date.getFullYear();

  const dayString: string = day < 10 ? `0${day}` : `${day}`;
  const monthString: string = month < 10 ? `0${month}` : `${month}`;

  return `${year}-${monthString}-${dayString}`;
};
