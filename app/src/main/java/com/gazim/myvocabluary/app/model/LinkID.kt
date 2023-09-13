package com.gazim.myvocabluary.app.model

data class LinkID(
    override val link: String = "",
    override val wordId: Int = 0,
    override val id: Int = 0,
) : ILinkID
