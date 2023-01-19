<template>
  <div class="calculator">
    <div class="display">0</div>
    <div class="btn">C</div>
    <div class="btn">+/-</div>
    <div class="btn">%</div>
    <div class="btn">÷</div>
    <div class="btn">7</div>
    <div class="btn">8</div>
    <div class="btn">9</div>
    <div class="btn">x</div>
    <div class="btn">4</div>
    <div class="btn">5</div>
    <div class="btn">6</div>
    <div class="btn">-</div>
    <div class="btn">1</div>
    <div class="btn">2</div>
    <div class="btn">3</div>
    <div class="btn">+</div>
    <div class="zero btn">0</div>
    <div class="btn">.</div>
    <div class="btn">=</div>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<style scoped>
.calculator {
  margin-top: 2%;
  font-size: 40px;
  text-align: center;
  margin-left: auto;

  margin-right: auto;

  display: grid;
  max-width: 50%;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: minmax(50px, auto);
}

.display {
  grid-column: 1 / 5;
}

.zero {
  grid-column: 1 /3;
}

.btn {
  padding: 10px;
  margin: 2px;
  border-radius: 10px;
  background: rgb(236, 140, 61);
}
</style>
