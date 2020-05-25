export class Vector3 {

    constructor(x, y, z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    multiply(factor) {
        this.x = this.x * factor;
        this.y = this.y * factor;
        this.z = this.z * factor;
        return this;
    }


}