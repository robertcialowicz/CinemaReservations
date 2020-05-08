import { TestBed } from '@angular/core/testing';

import { ShowsResolversService } from './resolvers.service';

describe('ShowsResolverService', () => {
  let service: ShowsResolversService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShowsResolversService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
