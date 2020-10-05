from unittest import TestCase
from unittest.mock import Mock, call


from Console import write_to_console, read_from_console
from Game import calculate_game_score, print_game, GameFactory
from Player import read_player, parse_input_player, InputPlayer
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
        read_from_console_mock = Mock(spec=read_from_console)
        parse_input_player_mock = Mock(spec=parse_input_player)
        write_to_console_mock = Mock(spec=write_to_console)
        read_player_mock = Mock(spec=read_player, return_value=InputPlayer.Player1)
        calculate_game_score_mock = Mock(spec=calculate_game_score, side_effect=player_reader_side_effect)
        print_game_mock = Mock(spec=print_game)

        tennis_game(write_to_console_mock,
                    read_from_console_mock,
                    parse_input_player_mock,
                    read_player_mock,
                    calculate_game_score_mock,
                    print_game_mock)

        write_to_console_mock.assert_called_with("Welcome to the Tennis Game!")
        read_player_mock.assert_has_calls([
            call(write_to_console_mock, read_from_console_mock, parse_input_player_mock),
            call(write_to_console_mock, read_from_console_mock, parse_input_player_mock),
            call(write_to_console_mock, read_from_console_mock, parse_input_player_mock),
            call(write_to_console_mock, read_from_console_mock, parse_input_player_mock)
        ])
        calculate_game_score_mock.assert_has_calls([
            call(GameFactory.make(), InputPlayer.Player1),
            call(GameFactory.make_using(Score.Fifteen, Score.Love), InputPlayer.Player1),
            call(GameFactory.make_using(Score.Thirty, Score.Love), InputPlayer.Player1),
            call(GameFactory.make_using(Score.Forty, Score.Love), InputPlayer.Player1)
        ])
        print_game_mock.assert_has_calls([
            call(write_to_console_mock, GameFactory.make_using(Score.Fifteen, Score.Love)),
            call(write_to_console_mock, GameFactory.make_using(Score.Thirty, Score.Love)),
            call(write_to_console_mock, GameFactory.make_using(Score.Forty, Score.Love)),
            call(write_to_console_mock, GameFactory.make_using(Score.Wins, Score.Love))
        ])
