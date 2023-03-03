import Calculator from "../Calculator.vue";
import { flushPromises, mount } from "@vue/test-utils";
import { describe, test, expect, vi, afterEach } from "vitest";
import axios from "axios";


describe("Calculator", () => {

  afterEach (() => {
    vi.restoreAllMocks();
  });

  test("renders a calculator", () => {
    const wrapper = mount(Calculator);
    const displayText = wrapper
      .find('[data-testid="display"]')
      .text();
    expect(displayText).toBe("0");
  });

  test("updates the display with numbers that are clicked", async () => {
    const wrapper = mount(Calculator);
    const button1 = wrapper.find('[data-testid="one"]');

    await button1.trigger("click");

    const displayText = wrapper.find('[data-testid="display"]').text();

    expect(displayText).toBe("1");
  });

  test("adds two numbers", async () => {
    const spy = vi.spyOn(axios, 'post');
    const wrapper = mount(Calculator);
    const button1 = wrapper.find('[data-testid="one"]');
    const button2 = wrapper.find('[data-testid="two"]');;
    const buttonPlus = wrapper.find('[data-testid="add"]');
    const buttonEquals = wrapper.find('[data-testid="equals"]');

    await button1.trigger("click");
    await buttonPlus.trigger("click");
    await button2.trigger("click");
    await buttonEquals.trigger("click");

    expect(spy).toHaveBeenCalledTimes(1);

    await flushPromises();
  });

  test("subtracts two numbers", async () => {
    const spy = vi.spyOn(axios, 'post');
    const wrapper = mount(Calculator);
    const button1 = wrapper.find('[data-testid="one"]');
    const button3 = wrapper.find('[data-testid="three"]');;
    const buttonMinus = wrapper.find('[data-testid="subtract"]');
    const buttonEquals = wrapper.find('[data-testid="equals"]');

    await button3.trigger("click");
    await buttonMinus.trigger("click");
    await button1.trigger("click");
    await buttonEquals.trigger("click");

    expect(spy).toHaveBeenCalledTimes(1);

    await flushPromises();
  });

  test("multiplies two numbers", async () => {
    const spy = vi.spyOn(axios, 'post');
    const wrapper = mount(Calculator);
    const button1 = wrapper.find('[data-testid="one"]');
    const button2 = wrapper.find('[data-testid="two"]');;
    const buttonMultiply = wrapper.find('[data-testid="multiply"]');
    const buttonEquals = wrapper.find('[data-testid="equals"]');
    const display = wrapper.find('[data-testid="display"]');

    await button2.trigger("click");
    await buttonMultiply.trigger("click");
    await button1.trigger("click");
    await buttonEquals.trigger("click");

    expect(spy).toHaveBeenCalledTimes(1);
  });

  test("divides two numbers", async () => {
    const spy = vi.spyOn(axios, 'post');
    const wrapper = mount(Calculator);
    const button1 = wrapper.find('[data-testid="one"]');
    const button2 = wrapper.find('[data-testid="two"]');;
    const buttonDivide = wrapper.find('[data-testid="divide"]');
    const buttonEquals = wrapper.find('[data-testid="equals"]');

    await button2.trigger("click");
    await buttonDivide.trigger("click");
    await button1.trigger("click");
    await buttonEquals.trigger("click");

    expect(spy).toHaveBeenCalledTimes(1);

    await flushPromises();
  });

  test("concatenates multiple number button clicks", async () => {
    const wrapper = mount(Calculator);
    const button1 = wrapper.find('[data-testid="one"]');
    const button2 = wrapper.find('[data-testid="two"]');;
    const button3 = wrapper.find('[data-testid="three"]');
    const button4 = wrapper.find('[data-testid="four"]');
    const button5 = wrapper.find('[data-testid="five"]');
    const button6 = wrapper.find('[data-testid="six"]');
    const button7 = wrapper.find('[data-testid="seven"]');
    const button8 = wrapper.find('[data-testid="eight"]');
    const button9 = wrapper.find('[data-testid="nine"]');
    const display = wrapper.find('[data-testid="display"]');

    await button1.trigger("click");
    await button2.trigger("click");
    await button3.trigger("click");
    await button4.trigger("click");
    await button5.trigger("click");
    await button6.trigger("click");
    await button7.trigger("click");
    await button8.trigger("click");
    await button9.trigger("click");

    expect(display.text()).toBe("123456789");
  });

  test("chain multiple operations together", async () => {
    const spy = vi.spyOn(axios, 'post');
    const wrapper = mount(Calculator);
    const button1 = wrapper.find('[data-testid="one"]');
    const button2 = wrapper.find('[data-testid="two"]');;
    const button3 = wrapper.find('[data-testid="three"]');
    const button4 = wrapper.find('[data-testid="four"]');
    const button5 = wrapper.find('[data-testid="five"]');
    const buttonPlus = wrapper.find('[data-testid="add"]');
    const buttonMinus = wrapper.find('[data-testid="subtract"]');
    const buttonMultiply = wrapper.find('[data-testid="multiply"]');
    const buttonDivide = wrapper.find('[data-testid="divide"]');
    const buttonEquals = wrapper.find('[data-testid="equals"]');

    await button1.trigger("click");
    await buttonPlus.trigger("click");
    await button2.trigger("click");
    await buttonMultiply.trigger("click");
    await button3.trigger("click");
    await buttonMinus.trigger("click");
    await button4.trigger("click");
    await buttonDivide.trigger("click");
    await button5.trigger("click");
    await buttonEquals.trigger("click");

    expect(spy).toHaveBeenCalledTimes(1);

    await flushPromises();
  });

  test("clear the input field with the clear button", async () => {
    const wrapper = mount(Calculator);
    const button1 = wrapper.find('[data-testid="one"]');
    const button2 = wrapper.find('[data-testid="two"]');;
    const buttonPlus = wrapper.find('[data-testid="add"]');
    const buttonEquals = wrapper.find('[data-testid="equals"]');
    const buttonClear = wrapper.find('[data-testid="clear"]');
    const display = wrapper.find('[data-testid="display"]');

    await button1.trigger("click");
    await buttonPlus.trigger("click");
    await button2.trigger("click");
    await buttonEquals.trigger("click");
    await buttonClear.trigger("click");

    await flushPromises();

    expect(display.text()).toBe("0");
  });
});