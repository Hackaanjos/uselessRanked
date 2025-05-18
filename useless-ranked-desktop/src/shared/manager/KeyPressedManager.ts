import { KeyboardEvent } from "../../models/KeyboardEvent";
import { API_URL } from "../../Config";
import { groupBy, map } from "lodash";
import { KeyPressedDTO } from "./dto/KeyPressedDTO";
import { CookieService } from "../service/CookieService";

export class KeyPressedManager {
    private static instance: KeyPressedManager;

    private constructor() { }

    public static getInstance(): KeyPressedManager {
        if (!KeyPressedManager.instance) {
            KeyPressedManager.instance = new KeyPressedManager();
        }
        return KeyPressedManager.instance;
    }

    public async sendKeyPressed(keyEventList: KeyboardEvent[]): Promise<void> {
        var groupedKeys = groupBy(keyEventList, (keyEvent) => keyEvent.keyCode);
        const requestBody = map(groupedKeys, (items, key) => new KeyPressedDTO(Number.parseInt(key), items.length));
        const jsessionId = await CookieService.getInstance().getSessionCookie();

        await fetch(`${API_URL}/keypressed`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Cookie': `JSESSIONID=${jsessionId}`
              },
              body: JSON.stringify(requestBody),
        })
    }
}