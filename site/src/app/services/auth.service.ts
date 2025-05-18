import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpRequestManager } from '../../utils/http/http-request-manager';
import { map } from 'rxjs/operators';

interface User {
  id: number;
  email: string;
  name: string;
  picture: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();
  public isLoggedIn$ = this.currentUser$.pipe(
    map(user => user !== null)
  );

  constructor(private http: HttpRequestManager) {
    this.checkAuthStatus();
  }

  private checkAuthStatus(): void {
    this.http.get<User>('auth/user').subscribe({
      next: (user) => {
        this.currentUserSubject.next(user);
      },
      error: () => {
        this.currentUserSubject.next(null);
      }
    });
  }

  login(): void {
    //const callback = encodeURIComponent(window.location.origin);
    window.location.href = `http://localhost:8080/oauth2/authorization/google`;
  }

  logout(): void {
    //const callback = encodeURIComponent(window.location.origin);
    window.location.href = `http://localhost:8080/api/auth/logout`;
  }

  isAuthenticated(): boolean {
    return this.currentUserSubject.value !== null;
  }
}
