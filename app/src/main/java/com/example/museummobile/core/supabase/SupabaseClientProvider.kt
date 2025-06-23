package com.example.museummobile.core.supabase

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import kotlin.time.Duration.Companion.seconds

object SupabaseClientProvider {


    val supabase =
        createSupabaseClient(
            supabaseUrl = "https://ixxpufbjinyviuodwxfl.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Iml4eHB1ZmJqaW55dml1b2R3eGZsIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDQ4MDE0MTMsImV4cCI6MjA2MDM3NzQxM30.p_KjP05f-xM0C487sc5whI6LHoiy_bUDgcABO2_Y_QI"
        ) {
            install(Auth){
                alwaysAutoRefresh = true
            }
            install(Postgrest)
            install(Realtime){
                reconnectDelay = 5.seconds
            }
        }

}
