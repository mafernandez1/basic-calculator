<template>
  <div class="container-fluid w-50">
    <h1>Basic Calculator</h1>

    <div class="row border">
      <div class="col-12 display-4 bg-light text-dark text-right">{{current || '0'}}</div>
    </div>
    <div class="row">
      <div @click="clear" class="col-3 border btn btn-danger">C</div>
      <div @click="addMemory" class="col-3 border btn btn-light">M+</div>
      <button
        type="button"
        @click="recallMemory"
        class="col-3 border btn btn-light"
        :disabled="!memValue"
      >MR</button>
      <button
        type="button"
        @click="clearMemory"
        class="col-3 border btn btn-light"
        :disabled="!memValue"
      >MC</button>
    </div>
    <div class="row">
      <div @click="sign" class="col-3 border btn btn-light">&plusmn;</div>
      <div @click="showPi" class="col-3 border btn btn-light">&Pi;</div>
      <div @click="squareRoot" class="col-3 border btn btn-light">&radic;</div>
      <div @click="divide" class="col-3 border btn btn-secondary">&divide;</div>
    </div>
    <div class="row">
      <div @click="append('7')" class="col-3 border btn btn-light">7</div>
      <div @click="append('8')" class="col-3 border btn btn-light">8</div>
      <div @click="append('9')" class="col-3 border btn btn-light">9</div>
      <div @click="multiply" class="col-3 border btn btn-secondary">&times;</div>
    </div>
    <div class="row">
      <div @click="append('4')" class="col-3 border btn btn-light">4</div>
      <div @click="append('5')" class="col-3 border btn btn-light">5</div>
      <div @click="append('6')" class="col-3 border btn btn-light">6</div>
      <div @click="subtract" class="col-3 border btn btn-secondary">&minus;</div>
    </div>
    <div class="row">
      <div @click="append('1')" class="col-3 border btn btn-light">1</div>
      <div @click="append('2')" class="col-3 border btn btn-light">2</div>
      <div @click="append('3')" class="col-3 border btn btn-light">3</div>
      <div @click="add" class="col-3 border btn btn-secondary">&plus;</div>
    </div>
    <div class="row">
      <div @click="append('0')" class="col-6 border btn btn-light text-right">0</div>
      <div @click="dot" class="col-3 border btn btn-light">.</div>
      <div @click="equal" class="col-3 border btn btn-secondary">&equals;</div>
    </div>
  </div>
</template>

<script>
const axios = require("axios");
export default {
  data() {
    return {
      previous: null,
      current: "",
      operator: null,
      operatorClicked: false,
      equalClicked: false,
      memValue: null,
      apiUrl: "http://localhost:8080/"
      //apiUrl: "http://192.168.99.100:8080/",
    };
  },
  mounted() {
    if (localStorage.number) {
      this.memValue = localStorage.number;
    }
  },
  methods: {
    setPrevious(operator) {
      this.previous = this.current;
      this.operator = operator;
      this.operatorClicked = true;
    },
    append(symbol) {
      if (this.operatorClicked || this.equalClicked) {
        this.current = "";
        this.operatorClicked = false;
        this.equalClicked = false;
      }
      if (this.canAppendNumber() || symbol === ".") {
        this.current =
          this.current === "0" && symbol !== "."
            ? symbol
            : `${this.current}${symbol}`;
      }
    },
    clear() {
      this.current = "";
    },
    sign() {
      if (this.current && this.current !== "0") {
        this.current =
          this.current.indexOf("-") !== -1 ? this.current.slice(1) : `-${this.current}`;
      }
    },
    addMemory() {
      if (
        this.current &&
        this.current !== "0" &&
        !isNaN(Number(this.current))
      ) {
        localStorage.number = this.current;
        this.memValue = this.current;
      }
    },
    recallMemory() {
      this.current = localStorage.number;
    },
    clearMemory() {
      localStorage.clear();
      this.memValue = null;
    },
    showPi() {
      // Since Pi is a constant that need all the decimal places.
      // I left the entire number without round it up to 2 decimal places
      this.current = `${Math.PI}`;
      // If we need only two decimals, we can uncomment the next line
      //this.current = `${Math.PI.toFixed(2)}`;
    },
    squareRoot() {
      if (parseFloat(this.current) >= 0) {
        axios
          .get(`${this.apiUrl}calc/sqrt/${parseFloat(this.current)}`)
          .then(response => {
            this.current = String(response.data);
            this.equalClicked = true;
            this.addMemory();
          });
      } else {
        this.current = "Invalid input";
      }
    },
    dot() {
      if (this.current.indexOf(".") === -1 || this.operatorClicked) {
        this.append(".");
      }
    },
    divide() {
      this.setPrevious("div");
    },
    multiply() {
      this.setPrevious("mult");
    },
    subtract() {
      this.setPrevious("sub");
    },
    add() {
      this.setPrevious("add");
    },
    equal() {
      if (this.current && this.current === "0" && this.operator === "div") {
        this.current = "Invalid input";
      } else {
        axios
          .get(
            `${this.apiUrl}calc/${this.operator}/${parseFloat(
              this.previous
            )}/${parseFloat(this.current)}`
          )
          .then(response => {
            this.current = String(response.data);
            this.equalClicked = true;
            this.addMemory();
          });
      }
    },
    canAppendNumber() {
      if (this.current.indexOf(".") === -1) {
        return this.current.length < 9;
      }
      const values = this.current.split(".");
      return values[1].length < 2;
    }
  }
};
</script>
