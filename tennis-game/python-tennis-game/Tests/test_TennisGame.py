from unittest import TestCase
from unittest.mock import Mock, call


from Console import write_to_console
from Game import calculate_game_score, print_game, GameFactory
from InputPlayer import InputPlayer
from Player import read_player
from Score import Score
from TennisGame import tennis_game


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
        write_to_console_mock = Mock(spec=write_to_console)
        read_player_mock = Mock(spec=read_player, return_value=InputPlayer.Player1)
        calculate_game_score_mock = Mock(spec=calculate_game_score, side_effect=player_reader_side_effect)
        print_game_mock = Mock(spec=print_game)

        tennis_game(write_to_console_mock,
                    read_player_mock,
                    calculate_game_score_mock,
                    print_game_mock)

        write_to_console_mock.assert_called_with("Welcome to the Tennis Game!")
        read_player_mock.assert_has_calls([
            call(),
            call(),
            call(),
            call(),
        ])
        calculate_game_score_mock.assert_has_calls([
            call(GameFactory.make(), InputPlayer.Player1),
            call(GameFactory.make_using(Score.Fifteen, Score.Love), InputPlayer.Player1),
            call(GameFactory.make_using(Score.Thirty, Score.Love), InputPlayer.Player1),
            call(GameFactory.make_using(Score.Forty, Score.Love), InputPlayer.Player1)
        ])
        print_game_mock.assert_has_calls([
            call(GameFactory.make_using(Score.Fifteen, Score.Love)),
            call(GameFactory.make_using(Score.Thirty, Score.Love)),
            call(GameFactory.make_using(Score.Forty, Score.Love)),
            call(GameFactory.make_using(Score.Wins, Score.Love))
        ])
