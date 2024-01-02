import {
  HttpHandlerFn,
  HttpInterceptorFn,
  HttpRequest,
} from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '@auth0/auth0-angular';
import { switchMap } from 'rxjs';

export const authInterceptor: HttpInterceptorFn = (
  req: HttpRequest<unknown>,
  next: HttpHandlerFn
) => {
  const auth: AuthService = inject(AuthService);

  return auth.isAuthenticated$.pipe(
    switchMap((isAuthenticated: boolean) => {
      if (isAuthenticated) {
        return auth.getAccessTokenSilently().pipe(
          switchMap((token: string) => {
            const apiReq: HttpRequest<unknown> = req.clone({
              setHeaders: {
                Authorization: `Bearer ${token}`,
              },
            });

            return next(apiReq);
          })
        );
      }

      return next(req);
    })
  );
};
