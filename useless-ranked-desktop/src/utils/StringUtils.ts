export class StringUtils {
    static splitOnce(str: string, sep: string): [string] | [string, string] {
        const index = str.indexOf(sep);
        if (index === -1) return [str];
        return [str.slice(0, index), str.slice(index + sep.length)];
    }
}
