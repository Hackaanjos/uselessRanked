export class KeyPressedDTO {

    keyCode: Number
    count: Number

    constructor(keyCode: Number, count: Number) {
        this.keyCode = keyCode;
        this.count = count;
    }
}