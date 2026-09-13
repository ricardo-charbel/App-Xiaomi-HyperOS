package edu.equipo2.xiaomios.menus

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDragHandle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.equipo2.xiaomios.R
import androidx.core.os.bundleOf
import com.rizzi.bouquet.PdfSource
import com.rizzi.bouquet.VerticalPdfReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import edu.equipo2.xiaomios.GlobalVariables
import edu.equipo2.xiaomios.MENUS
import edu.equipo2.xiaomios.ui.theme.AccentColor
import edu.equipo2.xiaomios.ui.theme.SecondaryBackgroundColor
import edu.equipo2.xiaomios.ui.theme.SecondaryTextColor
import java.io.File
import java.io.FileOutputStream

@Preview (
    showBackground = true,
    device = Devices.PHONE,
    showSystemUi = true
)
@Composable
fun MenuActividad(actividad: Actividad? = null){
    Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {BarraSuperior()}

    ) { innerPadding ->
        if (actividad != null){
            val state = rememberVerticalPdfReaderState(source = PdfSource.Asset(
                actividad.NombreArchivo
                ?: "" ))
            VerticalPdfReader(
                state = state,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Icon(
                    modifier = Modifier.size(128.dp),
                    painter = painterResource(id = R.drawable.ic_missing),
                    contentDescription = "Button Icon",
                    tint = SecondaryTextColor
                )

                Text(
                    text = "Actividad No Disponible",
                    color = SecondaryTextColor,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "El PDF que desea cargar no es valido.",
                    color = SecondaryTextColor,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
            }
        }
    }
}


@Composable
fun BarraSuperior(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(SecondaryBackgroundColor)
            .padding(top= 32.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Button(
            onClick = {
                GlobalVariables.actividadCargada = null
                GlobalVariables.menuActual = MENUS.PORTAFOLIO
                      },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            )
        ){
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Button Icon",
                tint = AccentColor
            )
        }

        Text(
            text = "Actividad Fundamental " + GlobalVariables.actividadCargada?.Numero.toString(),
            color = AccentColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

    }
}