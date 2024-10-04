package org.example.project

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.android.Android

actual fun createKtorEngine(): HttpClientEngineFactory<*> = Android