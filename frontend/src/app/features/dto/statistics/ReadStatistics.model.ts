interface MovieStatistics {
  readonly [title: string]: number;
}

export interface ReadStatistics {
  readonly moviesStatistics: MovieStatistics;
  readonly totalIncome: number;
}
