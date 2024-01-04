import { inject } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivateFn,
  RouterStateSnapshot,
} from '@angular/router';
import { AuthService } from '@auth0/auth0-angular';
import { tap } from 'rxjs';

export const adminGuard: CanActivateFn = (
  _route: ActivatedRouteSnapshot,
  _state: RouterStateSnapshot
) => {
  const auth: AuthService = inject(AuthService);

  return auth.isAuthenticated$.pipe(
    tap((isAuthenticated: boolean) => {
      if (!isAuthenticated) {
        auth.loginWithRedirect();

        return false;
      }

      return true;
    })
  );
};
