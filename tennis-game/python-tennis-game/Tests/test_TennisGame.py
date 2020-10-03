from unittest import TestCase
from unittest.mock import Mock, create_autospec

from Console import Console
from GameFactory import GameFactory
from GameScoreCalculator import GameScoreCalculator
from GameScorePrinter import GameScorePrinter
from InputPlayer import InputPlayer
from InputPlayerReader import InputPlayerReader
from Score import Score
from TennisGame import TennisGame


def player_reader_side_effect(*args):
    if args[0] == GameFactory.make() and args[1] == InputPlayer.Player1:
        return GameFactory.make_using(Score.Fifteen, Score.Love)
    if args[0] == GameFactory.make_using(Score.Fifteen, Score.Love) and args[1] == InputPlayer.Player1:
        return GameFactory.make_using(Score.Thirty, Score.Love)
    if args[0] == GameFactory.make_using(Score.Thirty, Score.Love) and args[1] == InputPlayer.Player1:
        return GameFactory.make_using(Score.Forty, Score.Love)
    if args[0] == GameFactory.make_using(Score.Forty, Score.Love) and args[1] == InputPlayer.Player1:
        return GameFactory.make_using(Score.Wins, Score.Love)


class TestTennisGame(TestCase):
    def test_start(self):
        console = Mock(Console)
        player_reader = Mock(InputPlayerReader)
        game_score_calculator = Mock(GameScoreCalculator)
        game_score_printer = Mock(GameScorePrinter)

        player_reader.read_player = Mock(return_value=InputPlayer.Player1)
        game_score_calculator.calculate.side_effect = player_reader_side_effect

        TennisGame(console, player_reader, game_score_calculator, game_score_printer).start()

        console.put.assert_called_with("Welcome to the Tennis Game!")
