class StringUtils {
    static splitOnce(str, sep) {
      const index = str.indexOf(sep);
      if (index === -1) return [str];
      return [str.slice(0, index), str.slice(index + sep.length)];
    }
  }
  
  module.exports = StringUtils;