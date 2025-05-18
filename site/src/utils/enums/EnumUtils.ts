export function getEnumKeyByValue<T extends Record<string, string | number>>(enumObj: T, value: T[keyof T]) {
  return Object.keys(enumObj).find(key => enumObj[key] === value);
}
