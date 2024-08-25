package com.example.accordionlistapplication

data class ItemList(
    val title: String,
    val description: String,
    val icon: String
) {
    companion object {
        fun getMockedItems(): List<ItemList> {
            return listOf(
                ItemList(
                    title = "Vandalismo y robo",
                    description = "El incremento en daños por vandalismo y robo, en promedio 13% en relación con años anteriores, sigue afectándonos.",
                    icon = "ride_ic_theft_light"
                ),
                ItemList(
                    title = "Inflación",
                    description = "Los costos de los servicios y materiales en dólares elevan el costo de la póliza.",
                    icon = "ride_ic_world_light"
                ),
                ItemList(
                    title = "Siniestralidad",
                    description = "El aumento en la frecuencia o severidad de los siniestros.",
                    icon = "ride_ic_crash_light"
                ),
                ItemList(
                    title = "Vehículo",
                    description = "La marca, modelo, antigüedad y uso del vehículo afectan la prima.",
                    icon = "ride_ic_medium_light"
                ),
                ItemList(
                    title = "Costos operativos",
                    description = "Los proveedores aumentan sus costos de funcionamiento y de reparación.",
                    icon = "ride_ic_calculator_light"
                )
            )
        }
    }
}