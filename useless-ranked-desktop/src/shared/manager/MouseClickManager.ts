import { API_URL } from "../../Config";
import { groupBy, map } from "lodash";
import { KeyPressedDTO } from "./dto/KeyPressedDTO";
import { CookieService } from "../service/CookieService";
import { MouseClickEvent } from "../../models/MouseClickEvent";
import { MouseClickDTO } from "./dto/MouseClickDTO";

export class MouseClickManager {
    private static instance: MouseClickManager;

    private constructor() { }

    public static getInstance(): MouseClickManager {
        if (!MouseClickManager.instance) {
            MouseClickManager.instance = new MouseClickManager();
        }
        return MouseClickManager.instance;
    }

    public async sendMouseClick(mouseClickList: MouseClickEvent[]): Promise<void> {
        const requestBody = new MouseClickDTO(mouseClickList.length)
        const jsessionId = await CookieService.getInstance().getSessionCookie();

        await fetch(`${API_URL}/mouseclick`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Cookie': `JSESSIONID=${jsessionId}`
              },
              body: JSON.stringify(requestBody),
        })
    }
}