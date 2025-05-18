import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

@Injectable({
    providedIn: 'root',
})
export class HttpRequestManager {

    constructor(private http: HttpClient) {}

    private getHeaders(): HttpHeaders {
        const token = localStorage.getItem('authToken');
        let headers = new HttpHeaders({
            'Content-Type': 'application/json'
        });

        if (token) {
            headers = headers.set('Authorization', `Bearer ${token}`);
        }

        return headers;
    }

    get<T>(endpoint: string, params?: HttpParams): Observable<T> {
        return this.http.get<T>(`/api/${endpoint}`, {
            headers: this.getHeaders(),
            params
        });
    }

    post<T>(endpoint: string, body: any): Observable<T> {
        return this.http.post<T>(`/api/${endpoint}`, body, {
            headers: this.getHeaders()
        });
    }

    put<T>(endpoint: string, body: any): Observable<T> {
        return this.http.put<T>(`/api/${endpoint}`, body, {
            headers: this.getHeaders()
        });
    }

    delete<T>(endpoint: string): Observable<T> {
        return this.http.delete<T>(`/api/${endpoint}`, {
            headers: this.getHeaders()
        });
    }
}
