package com.animation.exercise.data.network

import com.animation.exercise.data.model.BreathingCyclesDTO

/**
 * This would be an interface with annotation for GET, POST, etc. that would be created by Retrofit.
 * Since I have no real backend, this will just be implemented "as if it is the backend"
 */
interface CyclesAPI {

//    @GET("/backend/cycles")
    fun getCycles(): BreathingCyclesDTO

}