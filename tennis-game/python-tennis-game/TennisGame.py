from Console import Console
from GameFactory import GameFactory
from GameScoreCalculator import GameScoreCalculator
from GameScorePrinter import GameScorePrinter
from InputPlayerReader import InputPlayerReader


class TennisGame:
    def __init__(self,
                 console: Console,
                 player_reader: InputPlayerReader,
                 game_score_calculator: GameScoreCalculator,
                 game_score_printer: GameScorePrinter):
        self.console = console
        self.playerReader = player_reader
        self.game_score_calculator = game_score_calculator
        self.game_score_printer = game_score_printer

    def start(self):
        self.console.put("Welcome to the Tennis Game!")
        self.__game_loop()

    def __game_loop(self):
        game = GameFactory.make()
        while not game.completed():
            game = self.game_score_calculator.calculate(game, self.playerReader.read_player())
            self.game_score_printer.print(game)
