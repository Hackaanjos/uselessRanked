import { MouseClickEvent } from "../models/MouseClickEvent";
import { KeyboardEvent } from "../models/KeyboardEvent";
import { KeyPressedManager } from "../shared/manager/KeyPressedManager";
import { CookieService } from "../shared/service/CookieService";
import { MouseClickManager } from "../shared/manager/MouseClickManager";

async function syncTask(): Promise<void> {
  try {
    if (await CookieService.getInstance().getSessionCookie() == null) {
      scheduleSyncTask();
      return;
    }

    await syncKeyboardEvents();
    await syncMouseClickEvents();
  } catch (error) {
  }

  scheduleSyncTask();
}

async function syncKeyboardEvents(): Promise<void> {
  const keyboardEvents = await KeyboardEvent.findAll({
    order: [['id', 'ASC']],
    limit: 100,
  })

  if (keyboardEvents.length === 0) return;

  await KeyPressedManager.getInstance().sendKeyPressed(keyboardEvents);

  const idsToDelete = keyboardEvents.map(event => event.id);

  await KeyboardEvent.destroy({
    where: {
      id: idsToDelete
    }
  });
}

async function syncMouseClickEvents(): Promise<void> {
  const mouseClickEventList = await MouseClickEvent.findAll({
    order: [['id', 'ASC']],
    limit: 100,
  })

  if (mouseClickEventList.length === 0) return;

  await MouseClickManager.getInstance().sendMouseClick(mouseClickEventList);

  const idsToDelete = mouseClickEventList.map(event => event.id);

  await MouseClickEvent.destroy({
    where: {
      id: idsToDelete
    }
  });
}

function scheduleSyncTask() {
  setTimeout(() => {
    syncTask();
  }, 10000);
}

export default syncTask;