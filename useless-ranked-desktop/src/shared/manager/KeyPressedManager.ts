import { KeyboardEvent } from "../../models/KeyboardEvent";
import { API_URL } from "../../Config";
import { groupBy, map } from "lodash";
import { KeyPressedDTO } from "./dto/KeyPressedDTO";
import { CookieService } from "../service/CookieService";
import { getCharFromKeyCode } from "../KeyCodeMap";

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

        var requestBody = map(groupedKeys, (items, key) => {
            var keyCode = getCharFromKeyCode(Number.parseInt(key));
            console.log(keyCode);
            if (keyCode == null) return null;
            return new KeyPressedDTO(getCharFromKeyCode(Number.parseInt(key))?.toUpperCase()?.charCodeAt(0), items.length)
        })
        requestBody = requestBody.filter((item) => item != null);
        console.log(requestBody);
        if (requestBody.length == 0) return;

        const jsessionId = await CookieService.getInstance().getSessionCookie();

        await fetch(`${API_URL}/keypressed`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Cookie': `JSESSIONID=${jsessionId}`
            },
            body: JSON.stringify(requestBody),
        }).then((response) => {
            console.log(response);
        }).catch((error) => {
            console.error('Error:', error);
        });
    }
}