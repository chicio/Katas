from unittest import TestCase
from unittest.mock import Mock

from Console import Console
from InputPlayer import InputPlayer
from InputPlayerParser import InputPlayerParser
from read_player import InputPlayerReader


class TestInputPlayerReader(TestCase):
    def test_read_player(self):
        console = Mock(Console)
        input_player_parser = Mock(InputPlayerParser)

        console.read = Mock(return_value="1")
        input_player_parser.parse = Mock(return_value=InputPlayer.Player1)

        player = InputPlayerReader(console, input_player_parser).read_player()

        console.put.assert_has_calls(["Which player will play (1 or 2)?"])
        console.read.assert_called_once()
        input_player_parser.parse.assert_called_once_with(["1"])
        self.assertEqual(player, InputPlayer.Player1)

