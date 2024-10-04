package org.example.project

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun createKtorEngine(): HttpClientEngineFactory<*> = Darwin