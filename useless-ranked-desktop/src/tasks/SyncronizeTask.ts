import { session } from "electron";
import { KeyboardEvent } from "../models/KeyboardEvent";
import { KeyPressedManager } from "../shared/manager/KeyPressedManager";

async function syncTask(): Promise<void> {

    const cookies = await session.defaultSession.cookies.get({ name: 'JSESSIONID' })

    syncKeyboardEvents();

    scheduleSyncTask();
}

async function syncKeyboardEvents(): Promise<void> {
    const keyboardEvents = await KeyboardEvent.findAll({
        order: [['id', 'ASC']],
        limit: 50,
    })

    if (keyboardEvents.length === 0) return;

    await KeyPressedManager.getInstance().sendKeyPressed(keyboardEvents);


    //   const response = await fetch('https://sua-api.com/endpoint', {
    //     method: 'POST',
    //     headers: {
    //       'Content-Type': 'application/json',
    //       'Cookie': `JSESSIONID=${jsessionId}`, 
    //     },
    //     body: JSON.stringify(records),
    //   });

}

function scheduleSyncTask() {
    setTimeout(() => {
        syncTask();
    }, 10000);
}

export default syncTask;