import {
  HttpHandlerFn,
  HttpInterceptorFn,
  HttpRequest,
} from '@angular/common/http';

export const apiInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) => {
  const apiReq: HttpRequest<unknown> = req.clone({
    url: `http://localhost:8080/api/${req.url}`,
  });

  return next(apiReq);
};
