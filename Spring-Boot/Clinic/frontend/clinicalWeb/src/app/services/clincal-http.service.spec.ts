import { TestBed } from '@angular/core/testing';

import { ClincalHttpService } from './clincal-http.service';

describe('ClincalHttpService', () => {
  let service: ClincalHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClincalHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
