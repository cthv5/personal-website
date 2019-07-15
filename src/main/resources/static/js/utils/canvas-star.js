var COUNT_MAX = 10000;
var PI2 = Math.PI * 2;
var Starfield = /** @class */ (function () {
    function Starfield() {
        var _this = this;
        this.settings = {
            count: 100,
            demo: true,
            colors: [
                '#FFFFFF',
                '#DDDDDD',
                '#BBBBBB',
                '#999999',
                '#777777'
            ],
            size: {
                min: 0.1,
                range: 1
            },
            speed: 100,
            cursor: {
                manual: false,
                clickDuration: 1000,
                clickSize: 1000,
                x: 0,
                y: 0,
                size: {
                    radius: -200,
                    range: 200
                },
                impact: 8
            }
        };
        this.positionOffset = 0;
        this.colorSwitchCount = 200;
        this.width = window.innerWidth;
        this.height = window.innerHeight;
        this.currentCount = 500;
        this.currentSpeed = 100;
        this.stars = [];
        this.canvas = document.getElementsByTagName('canvas')[0] || document.createElement('canvas');
        this.dx = 0;
        this.dy = 0;
        this.cursorPosition = 0;
        this.fW2 = 0;
        this.fH2 = 0;
        document.body.appendChild(this.canvas);
        this.initCanvas();
        window.addEventListener('resize', function () { return _this.initCanvas(); });
        window.addEventListener('mousemove', function (e) { return _this.mouseMove(e); });
        window.addEventListener('mousedown', function (e) { return _this.mouseDown(e); });
        // this.initGui();
        this.initStarCount();
    }
    Object.defineProperty(Starfield.prototype, "starAttributes", {
        get: function () {
            return {
                size: Math.random(),
                x: Math.random(),
                y: Math.random(),
                angle: Math.random() * PI2,
                velocity: Math.random()
            };
        },
        enumerable: true,
        configurable: true
    });
    Starfield.prototype.initCanvas = function () {
        this.width = this.canvas.width = window.innerWidth;
        this.height = this.canvas.height = window.innerHeight;
        var ctx = this.canvas.getContext('2d');
        if (!ctx)
            throw 'failed to get context';
        this.ctx = ctx;
    };
    Starfield.prototype.initStarCount = function () {
        var _this = this;
        (_a = this.stars).push.apply(_a, Array.from({ length: COUNT_MAX }).map(function () { return _this.starAttributes; }));
        var _a;
    };
    // Starfield.prototype.initSpeed = function () {
    //     var oldPosition = Date.now() / this.currentSpeed;
    //     var newPosition = Date.now() / this.settings.speed;
    //     this.positionOffset += oldPosition - newPosition;
    //     this.currentSpeed = this.settings.speed;
    // };
    Starfield.prototype.mouseMove = function (e) {
        if (this.settings.demo) {
            this.settings.demo = false;
        }
        if (!this.settings.cursor.manual) {
            this.settings.cursor.x = e.x;
            this.settings.cursor.y = e.y;
        }
    };
    Starfield.prototype.mouseDown = function (e) {
        this.clickStart = Date.now();
    };
    // Starfield.prototype.initGui = function () {
    //     var _this = this;
    //     var gui = new dat.GUI();
    //     gui.add(this.settings, 'count', 100, COUNT_MAX, 10).listen().onChange(function () {
    //         _this.currentCount = _this.settings.count;
    //         _this.colorSwitchCount = ~~(_this.currentCount / 5);
    //     });
    //     gui.add(this.settings, 'speed', 1, 100, 1).onChange(function () {
    //         _this.initSpeed();
    //     });
    //     var size = gui.addFolder('size');
    //     size.open();
    //     size.add(this.settings.size, 'min', 0.1, 2, 0.1);
    //     size.add(this.settings.size, 'range', 0, 5, 0.1);
    //     var cursor = gui.addFolder('cursor');
    //     cursor.open();
    //     cursor.add(this.settings.cursor, 'manual').listen();
    //     cursor.add(this.settings.cursor, 'x').listen();
    //     cursor.add(this.settings.cursor, 'y').listen();
    //     cursor.add(this.settings.cursor, 'clickDuration', 10, 5000);
    //     cursor.add(this.settings.cursor, 'clickSize', 0, 3000);
    //     cursor.add(this.settings.cursor.size, 'radius', -1000, 3000).listen();
    //     cursor.add(this.settings.cursor.size, 'range', 0, 1000).listen();
    //     cursor.add(this.settings.cursor, 'impact', 0, 20, 0.1);
    //     var colors = gui.addFolder('colors');
    //     colors.open();
    //     colors.addColor(this.settings.colors, '0');
    //     colors.addColor(this.settings.colors, '1');
    //     colors.addColor(this.settings.colors, '2');
    //     colors.addColor(this.settings.colors, '3');
    //     colors.addColor(this.settings.colors, '4');
    //     gui.add(this.settings, 'demo').listen();
    // };
    Starfield.prototype.demo = function () {
        var count = 1000;
        var mod = (Date.now()) % (count * 2);
        this.settings.count = 500 + (mod > count ? count * 2 - mod : mod);
        this.currentCount = this.settings.count;
        this.colorSwitchCount = ~~(this.currentCount / 5);
    };
    Starfield.prototype.draw = function () {
        this.ctx.clearRect(0, 0, this.width, this.height);
        var colorIndex = 0;
        var position = this.positionOffset + Date.now() / this.currentSpeed;
        var baseWidth = Math.ceil(position) * this.width;
        var baseHeight = Math.ceil(position) * this.height;
        if (this.settings.demo) {
            this.cursorPosition = position / 10;
            this.fW2 = this.width * 0.5;
            this.fH2 = this.height * 0.5;
            this.settings.cursor.x = ~~(this.fW2 + this.fW2 * Math.sin(this.cursorPosition) * Math.tan(Math.sin(this.cursorPosition * 1.5)));
            this.settings.cursor.y = ~~(this.fH2 + this.fH2 * Math.sin(this.cursorPosition * 2) * Math.tan(Math.cos(this.cursorPosition)));
        }
        for (var i = 0; i < this.currentCount; i += 1) {
            var star = this.stars[i];
            var x = (baseWidth + star.x * this.width + Math.sin(star.angle) * position) % this.width;
            var y = (baseHeight + star.y * this.height + Math.cos(star.angle) * position) % this.height;
            if (i % this.colorSwitchCount === 0) {
                this.ctx.fill();
                this.ctx.fillStyle = this.settings.colors[colorIndex];
                colorIndex += 1;
                this.ctx.beginPath();
            }
            var impact = 0;
            if (this.settings.cursor.impact > 0) {
                var cursorRadius = this.settings.cursor.size.radius;
                if (this.clickStart) {
                    var elapsed = Date.now() - this.clickStart;
                    if (this.settings.cursor.clickDuration < elapsed) {
                        this.clickStart = undefined;
                    }
                    else {
                        cursorRadius += (elapsed / this.settings.cursor.clickDuration) * this.settings.cursor.clickSize;
                    }
                }
                var distance = Math.sqrt(Math.pow(x - this.settings.cursor.x, 2) + Math.pow(y - this.settings.cursor.y, 2));
                if (cursorRadius < distance && distance < cursorRadius + this.settings.cursor.size.range) {
                    impact = this.settings.cursor.impact * Math.sin((distance - cursorRadius) * Math.PI / this.settings.cursor.size.range);
                }
            }
            this.ctx.moveTo(x, y);
            this.ctx.arc(x, y, star.size * this.settings.size.range + this.settings.size.min + impact, 0, 360);
        }
        this.ctx.fill();
    };
    return Starfield;
}());
var starfield = new Starfield();
var draw = starfield.draw.bind(starfield);
var demo = starfield.demo.bind(starfield);
function run() {
    requestAnimationFrame(run);
    demo();
    draw();
}
run();