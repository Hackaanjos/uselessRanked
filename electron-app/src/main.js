const { app, BrowserWindow } = require('electron');
const path = require('node:path');
const gkmLogger = require('./shared/gkm/gkm-logger');

if (require('electron-squirrel-startup')) {
  app.quit();
}

app.commandLine.appendSwitch('gtk-version', '3')

const createWindow = () => {
  const mainWindow = new BrowserWindow({
    width: 800,
    height: 600,
    webPreferences: {
      preload: path.join(__dirname, 'preload.js'),
    },
  });

  mainWindow.loadFile(path.join(__dirname, 'index.html'));

  mainWindow.webContents.openDevTools();
};

app.whenReady().then(() => {
  createWindow();
  gkmLogger.initialize();

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