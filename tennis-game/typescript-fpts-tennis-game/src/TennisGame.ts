import {fold, Option, some} from "fp-ts/Option";
import {pipe} from "fp-ts/pipeable";

export const tennisGame: () => string = () => "test"

export const optionString: Option<string> = some("Ciao")

console.log(pipe(optionString, fold(() => "none", value => value)));
console.log(tennisGame());
