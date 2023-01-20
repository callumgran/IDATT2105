<template>
  <div id="calculator">
    <div id="display">{{ disp }}</div>
    <div @click="clear()" class="btn operator">C</div>
    <div @click="sign()" class="btn operator">+/-</div>
    <div @click="percent()" class="btn operator">%</div>
    <div @click="divide()" class="btn operator">÷</div>
    <div @click="add(7)" class="btn">7</div>
    <div @click="add(8)" class="btn">8</div>
    <div @click="add(9)" class="btn">9</div>
    <div @click="multiply()" class="btn operator">x</div>
    <div @click="add(4)" class="btn">4</div>
    <div @click="add(5)" class="btn">5</div>
    <div @click="add(6)" class="btn">6</div>
    <div @click="subtract()" class="btn operator">-</div>
    <div @click="add(1)" class="btn">1</div>
    <div @click="add(2)" class="btn">2</div>
    <div @click="add(3)" class="btn">3</div>
    <div @click="addition()" class="btn operator">+</div>
    <div @click="add(0)" class="btn" id="zero">0</div>
    <div @click="dot()" class="btn operator">.</div>
    <div @click="equals()" class="btn operator">=</div>
  </div>
  <div id="log">
    <div id="log_title">Latest calculations</div>
    <div v-bind:key="item" v-for="item in log">
      {{ item }}
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  data() {
    return {
      curr: '0' as string,
      prev: '0' as string,
      ans: '0' as string,
      disp: '0' as string,
      operation: '' as string,
      operationPressed: false as boolean,
      operator: null as ((a: number, b: number) => number) | null,
      log: [] as Array<string>,
    };
  },
  methods: {
    clear() {
      this.prev = '0';
      this.curr = '0';
      this.ans = '0';
      this.disp = '0';
    },
    sign() {
      this.curr = this.curr.charAt(0) === '-' ? this.curr.slice(1) : `-${this.curr}`;
      this.disp = this.curr;
    },
    percent() {
      this.curr = `${parseFloat(this.curr) / 100}`;
      this.disp = this.curr;
    },
    add(number: number) {
      if (this.operationPressed === true) {
        this.curr = '0';
        this.operationPressed = false;
      }
      if (this.curr === '0') this.curr = `${number}`;
      else this.curr = `${this.curr}${number}`;
      this.disp = this.curr;
    },
    dot() {
      if (this.operationPressed === true) {
        this.curr = '0';
        this.operationPressed = false;
      }
      if (this.curr.indexOf('.') === -1) this.curr = `${this.curr}.`;
      this.disp = this.curr;
    },
    divide() {
      this.checkPrev();
      this.operator = (a: number, b: number) => b / a;
      this.setPrev('/');
    },
    multiply() {
      this.checkPrev();
      this.operator = (a: number, b: number) => a * b;
      this.setPrev('*');
    },
    subtract() {
      this.checkPrev();
      this.operator = (a: number, b: number) => b - a;
      this.setPrev('-');
    },
    addition() {
      this.checkPrev();
      this.operator = (a: number, b: number) => a + b;
      this.setPrev('+');
    },
    checkPrev() {
      if (this.prev !== '0') {
        if (this.operator !== null) {
          this.curr = `${this.operator(parseFloat(this.curr), parseFloat(this.prev)).toFixed(3)}`;
          this.disp = this.curr;
        }
      }
    },
    setPrev(operation: string) {
      this.prev = this.curr;
      this.operationPressed = true;
      this.operation = operation;
    },
    equals() {
      if (this.operator !== null) {
        let currDec: number = 0;
        let prevDec: number = 0;
        if (this.curr.indexOf('.') !== -1) currDec = this.curr.length - this.curr.indexOf('.') - 1;
        if (this.prev.indexOf('.') !== -1) prevDec = this.prev.length - this.prev.indexOf('.') - 1;
        this.ans = `${this.operator(parseFloat(this.curr), parseFloat(this.prev)).toFixed(
          prevDec !== 0 || currDec !== 0 ? (prevDec > currDec ? prevDec : currDec) : 0,
        )}`;
      }

      this.disp = this.ans;
      if (this.operation != '') this.addToLog();
      this.prev = '0';
      this.curr = '0';
      this.operation != '';
    },
    addToLog() {
      if (this.log.length < 10) this.log[this.log.length] = `${this.prev} ${this.operation} ${this.curr} = ${this.ans}`;
      this.log = this.log.map((_, i, a) => a[(i + a.length - 1) % a.length]);
      if (this.log.length >= 10) this.log[0] = `${this.prev} ${this.operation} ${this.curr} = ${this.ans}`;
    },
  },
});
</script>

<style scoped>
@font-face {
  font-family: 'Roboto';
  src: url('roboto.eot');
  src: url('roboto.eot?#iefix') format('embedded-opentype'), url('roboto.woff') format('woff'),
    url('roboto.ttf') format('truetype'), url('roboto.svg#svgFontName') format('svg');
}

#log {
  border: 3px solid black;
  border-radius: 10px;
  max-width: 50%;
  margin: auto;
  margin-top: 2%;
  text-align: center;
  font-size: 20px;
  font-family: 'Roboto', sans-serif;
}

#log #log_title {
  font-size: 40px;
}

#calculator {
  margin-top: 2%;
  font-size: 40px;
  font-family: 'Roboto', sans-serif;
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  display: grid;
  max-width: 50%;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: minmax(50px, auto);
}

#display {
  grid-column: 1 / 5;
}

#zero {
  grid-column: 1 /3;
}

.btn {
  padding: 10px;
  margin: 2px;
  border-radius: 10px;
  background: darkgray;
  user-select: none;
  -moz-user-select: none;
  -webkit-user-select: none;
  -ms-user-select: none;
}

.btn:hover {
  cursor: pointer;
  opacity: 0.8;
}

.operator {
  background: lightslategray;
}
</style>
