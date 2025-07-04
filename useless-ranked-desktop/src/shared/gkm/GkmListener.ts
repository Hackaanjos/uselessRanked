import { EventEmitter2 } from 'eventemitter2';
import path from 'path';
import { StringUtils } from '../../utils/StringUtils';
import { spawn, IPty } from 'node-pty';
import { app } from 'electron';
import GkmKeyEvent from './models/GkmKeyEvent';
import { GkmMouseEvent } from './models/GkmMouseEvent';

export class GkmListener {
    private static instance: GkmListener;
    private events: EventEmitter2;
    private gkmProcess: IPty | null = null;

    private constructor() {
        this.events = new EventEmitter2({ wildcard: true });
    }

    public static getInstance(): GkmListener {
        if (!GkmListener.instance) {
            GkmListener.instance = new GkmListener();
        }
        return GkmListener.instance;
    }

    public start(): void {
        if (this.gkmProcess) return;

        const resourcesPath = app.isPackaged ? process.resourcesPath : app.getAppPath();
        const jarPath = path.join(resourcesPath, 'lib', 'gkm.jar');

        this.gkmProcess = spawn('java', ['-jar', jarPath], {})

        this.gkmProcess.onData((data: string) => {
            const lines = data.split(/\r\n|\r|\n/).filter((line) => line.trim() !== '');

            for (const line of lines) {
                const parts = StringUtils.splitOnce(line, ':');
                if (!parts || parts.length < 2) continue;

                const eventType = parts[0]

                switch (eventType) {
                    case 'keyboard':
                        const keyEvent: GkmKeyEvent = JSON.parse(parts[1]!.toString());
                        this.events.emit(`keyboard:${keyEvent.eventType}`, keyEvent);
                        break;
                    case 'mouse':
                        const mouseEvent: GkmMouseEvent = JSON.parse(parts[1]!.toString());
                        this.events.emit(`mouse:${mouseEvent.eventType}`, mouseEvent);
                        break;
                }

            }
        });
    }

    public onKeyPressed(callback: (event: GkmKeyEvent) => void): void {
        this.events.on('keyboard:PRESSED', callback);
    }

    public onMouseClicked(callback: (event: GkmMouseEvent) => void): void {
        this.events.on('mouse:CLICKED', callback);
    }

    public stop(): void {
        if (this.gkmProcess) {
            this.gkmProcess.kill();
            this.gkmProcess = null;
        }
    }
}