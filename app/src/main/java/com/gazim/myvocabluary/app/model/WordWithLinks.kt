package com.gazim.myvocabluary.app.model

data class WordWithLinks(
    override val word: IWordID = WordID(),
    override val links: List<ILinkID> = emptyList(),
) : IWordWithLinks