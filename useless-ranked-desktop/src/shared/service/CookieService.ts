import { session } from "electron";
export class CookieService {
    private static instance: CookieService;

    private constructor() {}

    public static getInstance(): CookieService {
        if (!CookieService.instance) {
            CookieService.instance = new CookieService();
        }
        return CookieService.instance;
    }

   public async getCookie(name: string): Promise<string | null> {
        const cookies = await session.defaultSession.cookies.get({ name: name });
        if (cookies.length > 0) {
            return cookies[0].value;
        }
        return null;
    }

    public async getSessionCookie(): Promise<string | null> {
       return await this.getCookie('JSESSIONID');
    }
}
