import { Routes } from '@angular/router';
import { LocationComponent } from './location/location.component';
import { FormLocationComponent } from './form-location/form-location.component';

export const routes: Routes = [
    { path: 'locations', component: LocationComponent },
    { path: 'locations/novo', component: FormLocationComponent },
    { path: '', redirectTo: 'locations', pathMatch: 'full' }
];