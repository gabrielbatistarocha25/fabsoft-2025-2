import { TestBed } from '@angular/core/testing';

import { VlanService } from './vlan.service';

describe('VlanService', () => {
  let service: VlanService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VlanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
