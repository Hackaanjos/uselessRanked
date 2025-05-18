const { app, BrowserWindow } = require('electron');
const path = require('node:path');
const Knex = require('knex');
const knexConfig = require('../knexfile');
const { Model } = require('objection');
const fs = require('fs');

const gkmLogger = require('./gkm/gkm-logger');

if (require('electron-squirrel-startup')) {
  app.quit();
}

app.commandLine.appendSwitch('gtk-version', '3')

const knex = Knex(knexConfig.default);

// Link o Objection ao Knex
Model.knex(knex);

const createWindow = () => {
  const mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
    },
  });

  if (process.env.NODE_ENV === 'development') {
    mainWindow.loadURL('http://localhost:4200');
    mainWindow.webContents.openDevTools();
    return;
  }

  mainWindow.loadFile('web/index.html');
};

app.whenReady().then(async () => {
  createWindow();
  gkmLogger.initialize();
  await knex.migrate.latest();
  logFilesAndFolders(__dirname);
  app.on('activate', () => {
    if (BrowserWindow.getAllWindows().length === 0) {
      createWindow();
    }
  });
});

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit();
  }
});

function logFilesAndFolders(dir, prefix = '') {
  try {
    const items = fs.readdirSync(dir);
    for (const item of items) {
      const fullPath = path.join(dir, item);
      const stat = fs.statSync(fullPath);

      if (stat.isDirectory()) {
        console.log(`${prefix}[DIR]  ${item}`);
        logFilesAndFolders(fullPath, prefix + '  ');
      } else {
        console.log(`${prefix}[FILE] ${item}`);
      }
    }
  } catch (err) {
    console.error(`Erro ao ler ${dir}:`, err);
  }
}