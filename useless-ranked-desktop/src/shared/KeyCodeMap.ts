const keyCodeMap: { [key: number]: string } = {
    // Letras
    30: 'A',
    48: 'B',
    46: 'C',
    32: 'D',
    18: 'E',
    33: 'F',
    34: 'G',
    35: 'H',
    23: 'I',
    36: 'J',
    37: 'K',
    38: 'L',
    50: 'M',
    49: 'N',
    24: 'O',
    25: 'P',
    16: 'Q',
    19: 'R',
    31: 'S',
    20: 'T',
    22: 'U',
    47: 'V',
    17: 'W',
    45: 'X',
    21: 'Y',
    44: 'Z',
  
    // Números
    2: '1',
    3: '2',
    4: '3',
    5: '4',
    6: '5',
    7: '6',
    8: '7',
    9: '8',
    10: '9',
    11: '0',
  
    // Símbolos
    12: '-',
    13: '=',
    26: '[',
    27: ']',
    39: ';',
    40: '\'',
    41: '`',
    43: '\\',
    51: ',',
    52: '.',
    53: '/',
  };
  
  export function getCharFromKeyCode(code: number): string | undefined {
    return keyCodeMap[code];
  }
  