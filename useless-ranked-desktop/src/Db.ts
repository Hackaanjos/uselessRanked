import { app } from 'electron';
import { Sequelize } from "sequelize";
import { initMouseClickEvent } from "./models/MouseClickEvent";
import { initKeyboardEvent } from "./models/KeyboardEvent";
import sqlite3 from 'sqlite3';
import path from 'path';

var basePath = app.isPackaged ? app.getPath('userData') : './data';
const dbPath = path.join(basePath, 'database.sqlite');


const sequelize = new Sequelize({
    dialect: "sqlite",
    dialectModule: sqlite3,
    storage: dbPath,
    logging: false,
});

initKeyboardEvent(sequelize);
initMouseClickEvent(sequelize);

export { sequelize };