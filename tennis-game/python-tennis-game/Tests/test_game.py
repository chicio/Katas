from unittest import TestCase
from unittest.mock import Mock

from console import write_to_console, read_from_console
from player import read_player, parse_input_player, InputPlayer


class TestGame(TestCase):
    def test_read_player(self):
        write_to_console_mock = Mock(spec=write_to_console)
        read_to_console_mock = Mock(spec=read_from_console, return_value="1")
        parse_input_player_mock = Mock(spec=parse_input_player, return_value=InputPlayer.Player1)

        player = read_player(write_to_console_mock, read_to_console_mock, parse_input_player_mock)

        self.assertEqual(player, InputPlayer.Player1)
        write_to_console_mock.assert_called_once_with("Which player will play (1 or 2)?")
        read_to_console_mock.assert_called_once()
        parse_input_player_mock.assert_called_once_with("1")

    def test_parse_input_player(self):
        a_player = parse_input_player("1")
        another_player = parse_input_player("2")
        yet_another_player = parse_input_player("invalid")

        self.assertEqual(a_player, InputPlayer.Player1)
        self.assertEqual(another_player, InputPlayer.Player2)
        self.assertEqual(yet_another_player, InputPlayer.UnknownPlayer)
