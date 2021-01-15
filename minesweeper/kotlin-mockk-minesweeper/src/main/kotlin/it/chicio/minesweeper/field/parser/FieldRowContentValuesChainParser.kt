package it.chicio.minesweeper.field.parser

class FieldRowContentValuesChainParser : FieldRowContentChainParser() {
    override fun canParse(row: String?): Boolean = isNotHeader(row) && isNotTermination(row)

    @Throws(RuntimeException::class)
    override fun parse(fieldsParsingStatus: FieldsParsingStatus): FieldsParsingStatus {
        val newFieldsParsingStatus = fieldsParsingStatus.copy()
        newFieldsParsingStatus
                .currentRowContent
                ?.let {
                    val columnsValues = it.split(" ").toTypedArray()
                    checkIfNumberOfValuesAreCorrect(newFieldsParsingStatus, columnsValues)
                    setNewFieldRow(newFieldsParsingStatus, columnsValues)
                }
        return newFieldsParsingStatus
    }

    private fun setNewFieldRow(newFieldsParsingStatus: FieldsParsingStatus, columnsValues: Array<String>) {
        newFieldsParsingStatus.currentField?.let {
            for (column in 0 until it.numberOfColumn) {
                it[newFieldsParsingStatus.currentRow, column] = columnsValues[column]
            }
            newFieldsParsingStatus.currentRow++
        }
    }

    private fun checkIfNumberOfValuesAreCorrect(newFieldsParsingStatus: FieldsParsingStatus, columnsValues: Array<String>) {
        if (columnsValues.size != newFieldsParsingStatus.currentField!!.numberOfColumn) {
            throw RuntimeException()
        }
    }

    private fun isNotHeader(row: String?): Boolean = !(row?.let { it.matches(Regex("(\\d \\d)")) } ?: true)

    private fun isNotTermination(row: String?): Boolean = row != "0 0"
}