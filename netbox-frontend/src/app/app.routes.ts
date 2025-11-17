import { Routes } from '@angular/router';

import { HomeComponent } from './home/home.component'; 

import { LocationComponent } from './location/location.component';
import { FormLocationComponent } from './form-location/form-location.component';
import { ManufacturerComponent } from './manufacturer/manufacturer.component';
import { FormManufacturerComponent } from './form-manufacturer/form-manufacturer.component';
import { SiteComponent } from './site/site.component';
import { FormSiteComponent } from './form-site/form-site.component';
import { RackComponent } from './rack/rack.component';
import { FormRackComponent } from './form-rack/form-rack.component';
import { VlanComponent } from './vlan/vlan.component';
import { FormVlanComponent } from './form-vlan/form-vlan.component';
import { DeviceModelComponent } from './device-model/device-model.component';
import { FormDeviceModelComponent } from './form-device-model/form-device-model.component';
import { DeviceComponent } from './device/device.component';
import { FormDeviceComponent } from './form-device/form-device.component';

export const routes: Routes = [
  // Rota Home
  { path: 'home', component: HomeComponent },

  // Location
  { path: 'locations', component: LocationComponent },
  { path: 'locations/novo', component: FormLocationComponent },
  { path: 'locations/alterar/:id', component: FormLocationComponent },

  // Site
  { path: 'sites', component: SiteComponent },
  { path: 'sites/novo', component: FormSiteComponent },
  { path: 'sites/alterar/:id', component: FormSiteComponent },

  // Manufacturer
  { path: 'manufacturers', component: ManufacturerComponent },
  { path: 'manufacturers/novo', component: FormManufacturerComponent },
  { path: 'manufacturers/alterar/:id', component: FormManufacturerComponent },

  // Rack
  { path: 'racks', component: RackComponent },
  { path: 'racks/novo', component: FormRackComponent },
  { path: 'racks/alterar/:id', component: FormRackComponent },

  // Vlan
  { path: 'vlans', component: VlanComponent },
  { path: 'vlans/novo', component: FormVlanComponent },
  { path: 'vlans/alterar/:id', component: FormVlanComponent },

  // DeviceModel
  { path: 'device-models', component: DeviceModelComponent },
  { path: 'device-models/novo', component: FormDeviceModelComponent },
  { path: 'device-models/alterar/:id', component: FormDeviceModelComponent },

  // Device
  { path: 'devices', component: DeviceComponent },
  { path: 'devices/novo', component: FormDeviceComponent },
  { path: 'devices/alterar/:id', component: FormDeviceComponent },

  // Rota Padrão (agora aponta para home)
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];