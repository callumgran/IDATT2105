<template>
  <div id="calculator">
    <div data-testid="display" id="display">{{ disp }}</div>
    <button @click="clear()" class="btn operator" data-testid="clear">C</button>
    <button @click="sign()" class="btn operator" data-testid="sign">+/-</button>
    <button @click="addOperator('%')" class="btn operator" data-testid="percent">%</button>
    <button @click="addOperator('/')" class="btn operator" data-testid="divide">÷</button>
    <button @click="addNumber(7)" class="btn" data-testid="seven">7</button>
    <button @click="addNumber(8)" class="btn" data-testid="eight">8</button>
    <button @click="addNumber(9)" class="btn" data-testid="nine">9</button>
    <button @click="addOperator('*')" class="btn operator" data-testid="multiply">x</button>
    <button @click="addNumber(4)" class="btn" data-testid="four">4</button>
    <button @click="addNumber(5)" class="btn" data-testid="five">5</button>
    <button @click="addNumber(6)" class="btn" data-testid="six">6</button>
    <button @click="addOperator('-')" class="btn operator" data-testid="subtract">-</button>
    <button @click="addNumber(1)" class="btn" data-testid="one">1</button>
    <button @click="addNumber(2)" class="btn" data-testid="two">2</button>
    <button @click="addNumber(3)" class="btn" data-testid="three">3</button>
    <button @click="addOperator('+')" class="btn operator" data-testid="add">+</button>
    <button @click="addNumber(0)" class="btn" data-testid="zero">0</button>
    <button @click="dot()" class="btn operator" data-testid="decimal">.</button>
    <button @click="equals()" class="btn operator" data-testid="equals">=</button>
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
import { calculate } from '../api/api';
import { useStore } from 'vuex';

export default defineComponent({
  data() {
    return {
      curr: '0' as string,
      prev: '0' as string,
      ans: '0' as string,
      disp: '0' as string,
      operation: '' as string,
      log: [] as Array<string>,
      store: useStore(),
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
      if (this.disp[0] === '-') this.disp = this.disp.slice(1);
      else this.disp = `-${this.disp}`;
    },
    dot() {
      if (!this.disp.includes('.')) this.disp = `${this.disp}.`;
    },
    async equals() {
      if (this.operation !== '') {
        this.curr = `${this.curr}${this.disp}`;
        const response = await calculate(this.store.state.token, this.curr);
        this.ans = JSON.parse(response.result);
        this.disp = this.ans;
        this.addToLog();
        this.curr = '0';
        this.operation = '';
      }
    },
    addNumber(number: number) {
      if (this.disp === '0') this.disp = `${number}`;
      else this.disp = `${this.disp}${number}`;
    },
    addOperator(operator: string) {
      this.operation = operator;
      if (this.curr === '0') this.curr = `${this.disp} ${operator} `;
      else this.curr = `${this.curr}${this.disp} ${operator} `;
      this.disp = '0';
    },
    addToLog() {
      if (this.log.length < 10) this.log[this.log.length] = `${this.curr} = ${this.ans}`;
      this.log = this.log.map((_, i, a) => a[(i + a.length - 1) % a.length]);
      if (this.log.length >= 10) this.log[0] = `${this.curr} = ${this.ans}`;
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
  background: #0ec58e;
}
</style>
